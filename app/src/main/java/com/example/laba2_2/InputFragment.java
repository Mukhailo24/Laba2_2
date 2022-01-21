package com.example.laba2_2;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
public class InputFragment extends Fragment {

    interface OnFragmentSendDataListener {
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);

        Button btnOk = view.findViewById(R.id.btnOk);
        RadioGroup colorRadioGroup = view.findViewById(R.id.rg_colors);
        RadioGroup priceRadioGroup = view.findViewById(R.id.rg_price);

        btnOk.setOnClickListener(v -> {
            int colorId = colorRadioGroup.getCheckedRadioButtonId();
            int priceId = priceRadioGroup.getCheckedRadioButtonId();
            if (colorId == -1 || priceId == -1) {
                Toast.makeText(getContext(),
                        "Вкажіть дані", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton priceRadioButton = priceRadioGroup.findViewById(priceId);
                RadioButton colorRadioButton = colorRadioGroup.findViewById(colorId);
                String price = priceRadioButton.getText().toString();
                String color = colorRadioButton.getText().toString();
                String data = "Ваше замовлення: колір - " + color + ", діапазон цін - " + price;
                fragmentSendDataListener.onSendData(data);
            }
        });

        return view;
    }
}