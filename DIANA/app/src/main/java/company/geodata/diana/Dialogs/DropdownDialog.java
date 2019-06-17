package company.geodata.diana.Dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import company.geodata.diana.R;

/**
 * Created by jcmate on 8/8/2017.
 */

@SuppressLint("ValidFragment")
public class DropdownDialog extends DialogFragment {
    ListView listViewDropdown;
    AlertDialog dialog;
    String[] array;
    EditText editText;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dropdown_dialog,null);

        listViewDropdown = (ListView) v.findViewById(R.id.listViewDropdown);
        listViewDropdown.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array));

        listViewDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(array[position]);
                dialog.dismiss();
            }
        });
        builder.setView(v).setTitle(this.getTag());
        dialog = builder.create();

        return dialog;
    }

    public DropdownDialog(EditText editText, String[] array){
        this.array = array;
        this.editText = editText;
    }
}