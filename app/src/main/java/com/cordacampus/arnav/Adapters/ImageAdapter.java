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
    private List<Company> companyList;
    private List<Company> filteredCompanyList;
    private CompanyFilter companyFilter;
    private LayoutInflater mInflater;

    public ImageAdapter(Context context, List<Company> companyList) {
        mInflater = LayoutInflater.from(context);
        this.filteredCompanyList = companyList;
        this.companyList = companyList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.grid_item_image);

            // Bind the data efficiently with the holder.
            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        new DownloadImageTask(holder.image).execute(filteredCompanyList.get(position).getLogo());

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
    }

    @Override
    public Filter getFilter() {
        if ( companyFilter == null)
            companyFilter = new CompanyFilter();

        return companyFilter;
    }

    private class CompanyFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                // No filter implemented we return all the list
                results.values = companyList;
                results.count = companyList.size();
            }
            else {
                // We perform filtering operation
                List<Company> nCompanyList = new ArrayList<Company>();
                String nConstraint = constraint.toString().toUpperCase();
                for (Company p : companyList) {
                    if (p.getName().toUpperCase().contains(nConstraint))
                        nCompanyList.add(p);
                }

                results.values = nCompanyList;
                results.count = nCompanyList.size();

            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                filteredCompanyList = (List<Company>) results.values;
                notifyDataSetChanged();
            }
        }

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
        return filteredCompanyList.get(position).getId();
    }

}