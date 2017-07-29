package com.example.willian.appcarrinho;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Willian on 26/07/2017.
 */
public class DeviceList extends ListActivity{

    private BluetoothAdapter meuBT;
    static String MAC_ADDRESS = "";
    Set<BluetoothDevice> SetBT;
    ArrayAdapter<String> ArrayBT;

    @Override
    protected void onCreate(Bundle savedInBundle){
        super.onCreate(savedInBundle);

        ArrayBT = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        meuBT = BluetoothAdapter.getDefaultAdapter();

        SetBT = meuBT.getBondedDevices();

        if(SetBT.size() > 0)
        {
            for(BluetoothDevice i : SetBT)
            {
                ArrayBT.add(i.getName() + "\n" + i.getAddress());
            }
            setListAdapter(ArrayBT);
        }


    }



//    private LayoutInflater layInf;
//    private ArrayList<BluetoothDevice> listaBT;
//    private int id;
//
//    public DeviceList(Context context, int res, ArrayList<BluetoothDevice> disp)
//    {
//        super(context,res,disp);
//        this.listaBT = disp;
//        layInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        id = res;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent)
//    {
//        convertView = layInf.inflate(id,null);
//        BluetoothDevice device = listaBT.get(position);
//
//        if(device != null)
//        {
//            TextView devName = (TextView) convertView.findViewById(R.id.DevName);
//            TextView devMAC = (TextView) convertView.findViewById(R.id.DevMAC);
//
//            if(devName != null) devName.setText(device.getName());
//            if(devMAC != null) devName.setText(device.getAddress());
//        }
//
//        return convertView;
//    }

}
