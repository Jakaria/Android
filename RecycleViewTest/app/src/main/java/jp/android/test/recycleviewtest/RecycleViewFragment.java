package jp.android.test.recycleviewtest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by jakaria on 10/6/2016.
 */

public class RecycleViewFragment extends Fragment implements ImageRequester.ImageRequesterResponse {

    private ImageView mPhotoImageView;
    private TextView mDescriptionTextView;
    private Photo mSelectedPhoto;

    private ArrayList<Photo> mPhotosList;
    private ImageRequester mImageRequester;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private RecycleViewAdapter mAdapter;

    private static final String PHOTO_KEY = "PHOTO";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mPhotosList = new ArrayList<>();
        mImageRequester = new ImageRequester(getActivity());

        mAdapter = new RecycleViewAdapter(mPhotosList);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mPhotosList.size() == 0) {
            requestPhoto();
        }
    }

    private void requestPhoto() {
        try {
            mImageRequester.getPhoto();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receivedNewPhoto(final Photo newPhoto) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPhotosList.add(newPhoto);
                mAdapter.notifyItemInserted(mPhotosList.size());
            }
        });
    }
}
