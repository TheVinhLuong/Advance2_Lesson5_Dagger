package com.example.android.advance2_lesson2_mvp.screen.searchresult;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.advance2_lesson2_mvp.R;
import com.example.android.advance2_lesson2_mvp.screen.BaseRecyclerViewAdapter;
import com.example.android.advance2_lesson2_mvp.screen.data.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VinhTL on 18/10/2017.
 */

public class SearchResultAdapter extends BaseRecyclerViewAdapter<SearchResultAdapter.ItemViewHolder> {
    private static String TAG = SearchResultAdapter.class.getSimpleName();
    private List<User> mUsers;

    public SearchResultAdapter(@NonNull Context context, List<User> users) {
        super(context);
        Log.d(TAG, "passed users: " + users);
        mUsers = new ArrayList<>();
        mUsers.addAll(users);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutItem = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_item_search_result, parent, false);
        return new ItemViewHolder(layoutItem);
    }

  
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setData(mUsers.get(position));
    }
    
    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewUserLogin;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTextViewUserLogin = (TextView) itemView.findViewById(R.id.tvUserLogin);
        }

        public void setData(User user) {
            mTextViewUserLogin.setText(user.getLogin());
        }
    }
}
