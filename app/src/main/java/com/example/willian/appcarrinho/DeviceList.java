package com.example.willian.appcarrinho;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Willian on 26/07/2017.
 */
public class DeviceList extends ArrayAdapter<BluetoothDevice>{

    private LayoutInflater layInf;
    private ArrayList<BluetoothDevice> listaBT;
    private int id;

    public DeviceList(Context context, int res, ArrayList<BluetoothDevice> disp)
    {
        super(context,res,disp);
        this.listaBT = disp;
        layInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        id = res;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = layInf.inflate(id,null);
        BluetoothDevice device = listaBT.get(position);

        if(device != null)
        {
            TextView devName = (TextView) convertView.findViewById(R.id.DevName);
            TextView devMAC = (TextView) convertView.findViewById(R.id.DevMAC);

            if(devName != null) devName.setText(device.getName());
            if(devMAC != null) devName.setText(device.getAddress());
        }

        return convertView;
    }

}
