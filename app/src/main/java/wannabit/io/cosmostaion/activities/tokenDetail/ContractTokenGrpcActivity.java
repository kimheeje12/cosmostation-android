package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.contract.SendContractActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.tokenDetail.TokenDetailSupportHolder;

public class ContractTokenGrpcActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ImageView mToolbarSymbolImg;
    private TextView mToolbarSymbol, mToolbarChannel;
    private TextView mItemPerPrice;
    private ImageView mItemUpDownImg;
    private TextView mItemUpDownPrice;

    private CardView mBtnAddressPopup;
    private ImageView mKeyState;
    private TextView mTotalValue;
    private TextView mAddress, mEthAddress;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;

    private RelativeLayout mBtnIbcSend;
    private RelativeLayout mBtnSend;

    private Cw20TokenGrpcAdapter mAdapter;

    private Cw20Assets mCw20Asset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mToolbarSymbolImg = findViewById(R.id.toolbar_symbol_img);
        mToolbarSymbol = findViewById(R.id.toolbar_symbol);
        mToolbarChannel = findViewById(R.id.toolbar_channel);
        mItemPerPrice = findViewById(R.id.per_price);
        mItemUpDownImg = findViewById(R.id.ic_price_updown);
        mItemUpDownPrice = findViewById(R.id.dash_price_updown_tx);

        mBtnAddressPopup = findViewById(R.id.card_root);
        mKeyState = findViewById(R.id.img_account);
        mAddress = findViewById(R.id.account_Address);
        mEthAddress = findViewById(R.id.eth_address);
        mTotalValue = findViewById(R.id.total_value);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnIbcSend = findViewById(R.id.btn_ibc_send);
        mBtnSend = findViewById(R.id.btn_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mCw20Asset = getIntent().getParcelableExtra("cw20Asset");
        mToolbarChannel.setVisibility(View.GONE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new Cw20TokenGrpcAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUpdateView();
            }
        });

        onUpdateView();
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnIbcSend.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {
        if (mCw20Asset != null) {
            Picasso.get().load(mCw20Asset.logo).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(mToolbarSymbolImg);
            mToolbarSymbol.setText(mCw20Asset.denom.toUpperCase());

            mItemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), mCw20Asset.denom));
            mItemUpDownPrice.setText(WDp.dpValueChange(getBaseDao(), mCw20Asset.denom));
            final BigDecimal lastUpDown = WDp.valueChange(getBaseDao(), mCw20Asset.denom);
            if (lastUpDown.compareTo(BigDecimal.ZERO) > 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageResource(R.drawable.ic_price_up);
            } else if (lastUpDown.compareTo(BigDecimal.ZERO) < 0) {
                mItemUpDownImg.setVisibility(View.VISIBLE);
                mItemUpDownImg.setImageResource(R.drawable.ic_price_down);
            } else {
                mItemUpDownImg.setVisibility(View.INVISIBLE);
            }

            mBtnAddressPopup.setCardBackgroundColor(ContextCompat.getColor(ContractTokenGrpcActivity.this, mChainConfig.chainBgColor()));
            setAccountKeyStatus(this, mAccount, mChainConfig, mKeyState);
            mAddress.setText(mAccount.address);
            setEthAddress(mChainConfig, mEthAddress);
            mTotalValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), mCw20Asset.denom, mCw20Asset.getAmount(), mCw20Asset.decimal));
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressPopup)) {
            onClickQrCopy(mChainConfig, mAccount);

        } else if (v.equals(mBtnIbcSend)) {
            Toast.makeText(getBaseContext(), R.string.error_prepare, Toast.LENGTH_SHORT).show();
            return;

        } else if (v.equals(mBtnSend)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getBaseContext(), SendContractActivity.class);
            intent.putExtra("mCw20SendContract", mCw20Asset.contract_address);
            startActivity(intent);
        }
    }

    private class Cw20TokenGrpcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new TokenDetailSupportHolder(getLayoutInflater().inflate(R.layout.item_amount_detail, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            TokenDetailSupportHolder holder = (TokenDetailSupportHolder) viewHolder;
            holder.onBindCw20Token(ContractTokenGrpcActivity.this, mBaseChain, getBaseDao(), mCw20Asset);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
