package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.FeeInfo;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class SwapCoinListDialog extends DialogFragment {

    public static String WATCH_ADDRESS = "WatchingAddressDialog";

    private OnSelectChainsDialogResult mSelectChainsDialogResult;

    private ConstraintLayout mDialogLayout;
    private TextView mDialogTitle;
    private RecyclerView mRecyclerView;
    private SwapChainListAdapter mSwapChainListAdapter;
    private LinearLayout mBtnLayer;
    private Button mBtnLeft, mBtnRight;

    private ArrayList<String> mSwapCoinList;
    private ArrayList<FeeInfo.FeeData> mFeeDataList;
    private ArrayList<Coin> mSendCoinList;
    private ArrayList<BaseChain> mWatchAddressChainList;

    private Set<BaseChain> selectedSet = Sets.newHashSet();

    public static SwapCoinListDialog newInstance(Bundle bundle) {
        SwapCoinListDialog frag = new SwapCoinListDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_template_recycler, null);
        mSwapCoinList = getArguments().getStringArrayList("denoms");
        mFeeDataList = (ArrayList<FeeInfo.FeeData>) getArguments().getSerializable("feeDatas");
        mSendCoinList = (ArrayList<Coin>) getArguments().getSerializable("sendCoins");
        mWatchAddressChainList = (ArrayList<BaseChain>) getArguments().getSerializable("watchAddressChains");
        mDialogLayout = view.findViewById(R.id.dialog_layout);
        mDialogTitle = view.findViewById(R.id.dialog_title);
        mBtnLayer = view.findViewById(R.id.btn_layer);
        mBtnLeft = view.findViewById(R.id.btn_left);
        mBtnRight = view.findViewById(R.id.btn_right);

        mDialogLayout.setBackgroundResource(R.drawable.layout_trans_with_border);
        if (getTargetRequestCode() == 8500) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_in));
        } else if (getTargetRequestCode() == 8501) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_coin_swap_out));
        } else if (getTargetRequestCode() == 8502) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_fee_denom));
        } else if (getTargetRequestCode() == 8503) {
            mDialogTitle.setText(getTargetFragment().getString(R.string.str_select_to_send_coin));
        } else {
            mDialogTitle.setText(getSActivity().getString(R.string.str_select_chains));
            mBtnLayer.setVisibility(View.VISIBLE);
            mBtnLeft.setOnClickListener(View -> {
                getDialog().dismiss();
            });
            mBtnRight.setOnClickListener(View -> {
                if (selectedSet.size() >= 1) {
                    mSelectChainsDialogResult.SelectedChains(new Gson().toJson(selectedSet));
                    getDialog().dismiss();
                    Toast.makeText(getContext(), R.string.str_imported_chains, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), R.string.error_no_select_chains, Toast.LENGTH_SHORT).show();
                }
            });
        }
        mRecyclerView = view.findViewById(R.id.recycler);
        mSwapChainListAdapter = new SwapChainListAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mSwapChainListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }

    private class SwapChainListAdapter extends RecyclerView.Adapter<SwapChainListAdapter.SwapChainHolder> {
        private static final int TYPE_SWAP_LIST = 0;
        private static final int TYPE_FEE_LIST = 1;
        private static final int TYPE_SEND_COIN_LIST = 2;
        private static final int TYPE_WATCHING_ADDRESS_LIST = 3;

        @NonNull
        @Override
        public SwapChainListAdapter.SwapChainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new SwapChainListAdapter.SwapChainHolder(getLayoutInflater().inflate(R.layout.item_dialog_swap_coin, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SwapChainListAdapter.SwapChainHolder holder, int position) {
            if (getItemViewType(position) == TYPE_SWAP_LIST) {
                onBindSwapListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_FEE_LIST) {
                onBindFeeListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_SEND_COIN_LIST) {
                onBindSendListItemViewHolder(holder, position);
            } else if (getItemViewType(position) == TYPE_WATCHING_ADDRESS_LIST) {
                onBindSelectedChainListItemViewHolder(holder, position);
            }
        }

        private void onBindSwapListItemViewHolder(SwapChainHolder holder, int position) {
            final String inputCoin = mSwapCoinList.get(position);
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, inputCoin, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedDenom", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        private void onBindFeeListItemViewHolder(SwapChainHolder holder, int position) {
            String denom = mFeeDataList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        private void onBindSendListItemViewHolder(SwapChainHolder holder, int position) {
            String denom = mSendCoinList.get(position).denom;
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), getSActivity().mChainConfig, denom, holder.coinName);

            holder.rootLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, resultIntent);
                    getDialog().dismiss();
                }
            });
        }

        private void onBindSelectedChainListItemViewHolder(SwapChainHolder holder, int position) {
            BaseChain baseChain = mWatchAddressChainList.get(position);
            ChainConfig chainConfig = ChainFactory.getChain(baseChain);
            WDp.setDpSymbolImg(getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.coinImg);
            WDp.setDpSymbol(getSActivity(), getSActivity().getBaseDao(), chainConfig, chainConfig.mainDenom(), holder.coinName);

            bindChainSelect(holder, position, baseChain);
        }

        private void bindChainSelect(SwapChainHolder holder, int position, BaseChain item) {
            if (selectedSet.contains(item)) {
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_selected));
                holder.rootLayer.setOnClickListener(v -> {
                    selectedSet.remove(item);
                    mSwapChainListAdapter.notifyItemChanged(position);
                });
            } else {
                holder.rootLayer.setBackground(ContextCompat.getDrawable(getSActivity(), R.drawable.box_round_unselected));
                holder.rootLayer.setOnClickListener(v -> {
                    selectedSet.add(item);
                    mSwapChainListAdapter.notifyItemChanged(position);
                });
            }
        }

        @Override
        public int getItemCount() {
            if (getTargetRequestCode() == 8500 || getTargetRequestCode() == 8501)
                return mSwapCoinList.size();
            else if (getTargetRequestCode() == 8502) return mFeeDataList.size();
            else if (getTargetRequestCode() == 8503) return mSendCoinList.size();
            else return mWatchAddressChainList.size();
        }

        @Override
        public int getItemViewType(int position) {
            if (getTargetRequestCode() == 8500 || getTargetRequestCode() == 8501)
                return TYPE_SWAP_LIST;
            else if (getTargetRequestCode() == 8502) return TYPE_FEE_LIST;
            else if (getTargetRequestCode() == 8503) return TYPE_SEND_COIN_LIST;
            else return TYPE_WATCHING_ADDRESS_LIST;
        }

        public class SwapChainHolder extends RecyclerView.ViewHolder {
            LinearLayout rootLayer;
            ImageView coinImg;
            TextView coinName;

            public SwapChainHolder(@NonNull View itemView) {
                super(itemView);
                rootLayer = itemView.findViewById(R.id.rootLayer);
                coinImg = itemView.findViewById(R.id.coinImg);
                coinName = itemView.findViewById(R.id.coinName);
            }
        }
    }

    private BaseActivity getSActivity() {
        return (BaseActivity) getActivity();
    }

    public void setSelectChainsDialogResult(OnSelectChainsDialogResult dialogResult) {
        mSelectChainsDialogResult = dialogResult;
    }

    public interface OnSelectChainsDialogResult {
        void SelectedChains(String selectedChain);
    }
}


