package com.example.fragmentcommunication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private Button button;
    private EditText editText;
    onMessageSendListener messageSendListener;

    public interface onMessageSendListener
    {
        public void onMessageSend(String message);
    }

    public MessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        button = view.findViewById(R.id.br);
        editText = view.findViewById(R.id.txt_message);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String message = editText.getText().toString();
                messageSendListener.onMessageSend((message));
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try{
            messageSendListener = (onMessageSendListener)activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement onMessageSend...");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        editText.setText("");
    }
}
