package com.cordacampus.arnav.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.cordacampus.arnav.Model.Company;
import com.cordacampus.arnav.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ImageAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<Company> companyList;
    private List<Company> filteredCompanyList;

    public ImageAdapter(Context context, List<Company> companyList) {
        this.context = context;
        this.filteredCompanyList = companyList;
        this.companyList = companyList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.item, null);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            // download logo from url
            new DownloadImageTask((ImageView) gridView.findViewById(R.id.grid_item_image))
                    .execute(filteredCompanyList.get(position).getLogo());


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = companyList.size();
                    results.values = companyList;
                } else {//do the search
                    List<Company> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (Company o : companyList)
                        if (o.getName().toUpperCase().startsWith(searchStr)) resultsData.add(o);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredCompanyList = (ArrayList<Company>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public int getCount() {
        return filteredCompanyList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredCompanyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}