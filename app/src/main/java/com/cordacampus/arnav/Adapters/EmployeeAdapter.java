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
    private Context context;
    private List<Employee> employees;
    private List<Employee> filteredEmployees;

    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.context = context;
        this.employees = employees;
        this.filteredEmployees = employees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView;

        if (convertView == null) {
            // Get the data item for this position
            Employee employee = (Employee) getItem(position);

            // get layout from mobile.xml
            listView = inflater.inflate(R.layout.employee_cell, null);

            // Lookup view for data population
            TextView employeeName = (TextView) listView.findViewById(R.id.employee_name);
            TextView employeeCompany = (TextView) listView.findViewById(R.id.employee_company);
            // Populate the data into the template view using the data object
            employeeName.setText(employee.getFirstName() + " " + employee.getName());
            employeeCompany.setText(employee.getCompany().getName());

        } else {
            listView = convertView;
        }

        return listView;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = employees.size();
                    results.values = employees;
                } else {//do the search
                    List<Employee> resultsData = new ArrayList<>();
                    String searchStr = constraint.toString().toUpperCase();
                    for (Employee o : employees)
                        if (o.getName().toUpperCase().startsWith(searchStr) || o.getFirstName().toUpperCase().startsWith(searchStr)) resultsData.add(o);
                    results.count = resultsData.size();
                    results.values = resultsData;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredEmployees = (ArrayList<Employee>) results.values;
                notifyDataSetChanged();
            }
        };
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
        return 0;
    }
}