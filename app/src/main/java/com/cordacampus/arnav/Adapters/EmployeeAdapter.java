package com.cordacampus.arnav.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.cordacampus.arnav.Model.Employee;
import com.cordacampus.arnav.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter implements Filterable {
    private List<Employee> employees;
    private List<Employee> filteredEmployees;
    private EmployeeFilter employeeFilter = new EmployeeFilter();
    private LayoutInflater mInflater;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        mInflater = LayoutInflater.from(context);
        this.employees = employees;
        this.filteredEmployees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.employee_cell, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.employee_name);
            holder.company = (TextView) convertView.findViewById(R.id.employee_company);

            // Bind the data efficiently with the holder.

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // If weren't re-ordering this you could rely on what you set last time
        holder.name.setText(filteredEmployees.get(position).getFirstName() + " " + filteredEmployees.get(position).getName());
        holder.company.setText(filteredEmployees.get(position).getCompany().getName());

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView company;
    }


    @Override
    public Filter getFilter() {
        if ( employeeFilter == null)
            employeeFilter = new EmployeeFilter();

        return employeeFilter;
    }

    private class EmployeeFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
                results.values = employees;
                results.count = employees.size();
            }
            else {
            // We perform filtering operation
                List<Employee> nEmployeeList = new ArrayList<Employee>();
                String nConstraint = constraint.toString().toUpperCase();
                for (Employee p : employees) {
                    if ((p.getFirstName().toUpperCase() + " " + p.getName().toUpperCase()).contains(nConstraint))
                        nEmployeeList.add(p);
                }

                results.values = nEmployeeList;
                results.count = nEmployeeList.size();

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
                filteredEmployees = (List<Employee>) results.values;
                notifyDataSetChanged();
            }
        }

    }

    @Override
    public int getCount() {
        return filteredEmployees.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredEmployees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}