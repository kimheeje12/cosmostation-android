package wannabit.io.cosmostaion.activities.txs.nft;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SEND_NFT;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.fragment.StepFeeSetFragment;
import wannabit.io.cosmostaion.fragment.StepMemoFragment;
import wannabit.io.cosmostaion.fragment.txs.nft.NFTSendStep0Fragment;
import wannabit.io.cosmostaion.fragment.txs.nft.NFTSendStep3Fragment;

public class NFTSendActivity extends BaseBroadCastActivity {

    private Toolbar mToolbar;
    private TextView mTitle;
    private ImageView mIvStep;
    private TextView mTvStep;
    private ViewPager mViewPager;
    private NFTSendAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mIvStep = findViewById(R.id.send_step);
        mTvStep = findViewById(R.id.send_step_msg);
        mViewPager = findViewById(R.id.view_pager);
        mTitle.setText(getString(R.string.str_send_nfts));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTSendActivity.this, R.drawable.step_4_img_1));
        mTvStep.setText(getString(R.string.str_send_nft_step_0));

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mTxType = CONST_PW_TX_SEND_NFT;

        mNftDenomId = getIntent().getStringExtra("mDenomId");
        mNftTokenId = getIntent().getStringExtra("mTokenId");
        mIrisResponse = (QueryOuterClass.QueryNFTResponse) getIntent().getSerializableExtra("mIrisResponse");

        mPageAdapter = new NFTSendAdapter(getSupportFragmentManager());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTSendActivity.this, R.drawable.step_4_img_1));
                    mTvStep.setText(getString(R.string.str_send_nft_step_0));
                } else if (i == 1) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTSendActivity.this, R.drawable.step_4_img_2));
                    mTvStep.setText(getString(R.string.str_send_nft_step_1));
                } else if (i == 2) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTSendActivity.this, R.drawable.step_4_img_3));
                    mTvStep.setText(getString(R.string.str_send_nft_step_2));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                } else if (i == 3) {
                    mIvStep.setImageDrawable(ContextCompat.getDrawable(NFTSendActivity.this, R.drawable.step_4_img_4));
                    mTvStep.setText(getString(R.string.str_send_nft_step_3));
                    mPageAdapter.mCurrentFragment.onRefreshTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        mViewPager.setCurrentItem(0);
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

    @Override
    public void onBackPressed() {
        onHideKeyboard();
        if (mViewPager.getCurrentItem() > 0) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            super.onBackPressed();
        }
    }

    public void onNextStep() {
        if (mViewPager.getCurrentItem() < mViewPager.getChildCount()) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
        }
    }

    public void onBeforeStep() {
        if (mViewPager.getCurrentItem() > 0) {
            onHideKeyboard();
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
        } else {
            onBackPressed();
        }
    }

    public void onSendNFT() {
        if (getBaseDao().isAutoPass()) {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = null;
            if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                broadcastTxRequest = Signer.getGrpcSendNftIrisReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress, mNftDenomId,
                        mNftTokenId, mIrisResponse, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());

            } else if (mBaseChain.equals(BaseChain.CRYPTO_MAIN)) {
                broadcastTxRequest = Signer.getGrpcSendNftCroReq(getAuthResponse(mBaseChain, mAccount), mAccount.address, mToAddress, mNftDenomId,
                        mNftTokenId, mTxFee, mTxMemo, getEcKey(mAccount), getBaseDao().getChainIdGrpc());
            }
            onBroadcastGrpcTx(mBaseChain, broadcastTxRequest);

        } else {
            Intent intent = new Intent(NFTSendActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, mTxType);
            intent.putExtra("toAddress", mToAddress);
            intent.putExtra("nftDenomId", mNftDenomId);
            intent.putExtra("nftTokenId", mNftTokenId);
            intent.putExtra("irisResponse", mIrisResponse);
            intent.putExtra("memo", mTxMemo);
            intent.putExtra("fee", mTxFee);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        }
    }

    private class NFTSendAdapter extends FragmentPagerAdapter {
        private ArrayList<BaseFragment> mFragments = new ArrayList<>();
        private BaseFragment mCurrentFragment;

        public NFTSendAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            mFragments.add(NFTSendStep0Fragment.newInstance());
            mFragments.add(StepMemoFragment.newInstance());
            mFragments.add(StepFeeSetFragment.newInstance());
            mFragments.add(NFTSendStep3Fragment.newInstance());
        }

        @Override
        public BaseFragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((BaseFragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

        public BaseFragment getCurrentFragment() {
            return mCurrentFragment;
        }

        public ArrayList<BaseFragment> getFragments() {
            return mFragments;
        }
    }
}
