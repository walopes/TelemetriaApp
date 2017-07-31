package com.example.willian.appcarrinho;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Willian on 26/07/2017.
 */
public class DeviceList extends ListActivity{

    private BluetoothAdapter meuBT;
    static String MAC_ADDRESS = "LALALALABunga";
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String info = ((TextView) v).getText().toString();
        //Toast.makeText(getApplicationContext(),"Info: " + info, Toast.LENGTH_LONG).show();

        String endMac = info.substring(info.length() - 17); //O '-17' eh para tirar os 17 campos do mac

        // Intent para voltar para a tela principal
        Intent retornaMAC = new Intent();
        retornaMAC.putExtra(MAC_ADDRESS,endMac);
        setResult(RESULT_OK, retornaMAC);
        finish();
    }

}
