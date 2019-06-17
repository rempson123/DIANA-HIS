package company.geodata.diana.Adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import company.geodata.diana.Fragments.PrescriptionFragment;
import company.geodata.diana.Model.Prescription;
import company.geodata.diana.R;

/**
 * Created by jcmate on 6/5/2017.
 */

public class PrescriptionsAdapter extends RecyclerView.Adapter<PrescriptionsAdapter.MyViewHolder> {

    private List<Prescription> list;
    public class MyViewHolder extends  RecyclerView.ViewHolder {
        public TextView medicine, genericName, dose, quantity, instructions;
        public LinearLayout linearLayoutRow;
        public ImageView imageViewRemove;
        public MyViewHolder(View view) {
            super(view);
            medicine = (TextView) view.findViewById(R.id.textViewMedicine);
            genericName = (TextView) view.findViewById(R.id.textViewGenericName);
            dose = (TextView) view.findViewById(R.id.textViewDose);
            quantity = (TextView) view.findViewById(R.id.textViewQuantity);
            instructions = (TextView) view.findViewById(R.id.textViewInstructions);
            linearLayoutRow = (LinearLayout) view.findViewById(R.id.LinearLayoutRow);
            imageViewRemove = (ImageView) view.findViewById(R.id.imageViewRemove);
        }
    }

    public PrescriptionsAdapter(List<Prescription> list){
        this.list= list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.prescription_list_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Prescription prescription = list.get(position);
        holder.medicine.setText(prescription.getMedicine());
        holder.genericName.setText(prescription.getGenericName());
        holder.dose.setText(prescription.getDose() + " " + prescription.getUnit());
        holder.quantity.setText(prescription.getQuantity() + " " + prescription.getDosage()+ "; " + prescription.getNumberOfTake() + "; " + prescription.getType());
        holder.instructions.setText(prescription.getRefillInstructions());
        holder.imageViewRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == PrescriptionFragment.position) {
                    PrescriptionFragment.update(new Prescription(), -1);
                }
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.linearLayoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrescriptionFragment.update(list.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
