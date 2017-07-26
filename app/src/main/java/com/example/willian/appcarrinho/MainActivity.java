package com.example.willian.appcarrinho;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
// novo abaixo
import java.util.Set;
import java.util.ArrayList;

import android.widget.Switch;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener; // VERIFICAR SE ESTE AQUI ESTA CERTO MESMO
import android.widget.TextView;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
//////////////


// Site: http://www.instructables.com/id/Android-Bluetooth-Control-LED-Part-2/
// https://developer.android.com/training/basics/firstapp/starting-activity.html

public class MainActivity extends AppCompatActivity {

    public Button botaoPareado;
    public ListView listaDev;
    public TextView textTop;
    //private Switch botaoPareado // trocar o botao por um switch

    private BluetoothAdapter meuBT = null;
    public Set Pareados = null;


    BroadcastReceiver btState = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String previousState = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
            String nowState = BluetoothAdapter.EXTRA_STATE;
            int state = intent.getIntExtra(nowState, -1);
            String newToast = "";

            switch (state) {
                /**
                 * The Bluetooth is begin turnig on. A message will send by the Toast.
                 */
                case (BluetoothAdapter.STATE_TURNING_ON): {
                    newToast = "Bluetooth is turning on! :)";
                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                    break;
                }
                /**
                 * The Bluetooth is begin turnig off. A message will send by the Toast.
                 */
                case (BluetoothAdapter.STATE_TURNING_OFF): {
                    newToast = "Bluetooth is turning off! :(";
                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                    break;
                }
                /**
                 * The Bluetooth is activated. The program will begin.
                 */
                case (BluetoothAdapter.STATE_ON): {
                    // TESTE
                    String address = meuBT.getAddress();
                    String name = meuBT.getName();
                    String statusText = address + ':' + name + '\n';
                    textTop.setText(statusText);
                    // TESTE
                    newToast = "Bluetooth is ON! :)";
                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                    /**
                     * Next, call the function to Pair the devices.
                     */
                    devicesPaired();
                    break;
                }
                /**
                 * The Bluetooth is off. The application will be closed.
                 */
                case (BluetoothAdapter.STATE_OFF): {
                    newToast = "Bluetooth is OFF! Program will shut down in a few...";
                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginApp();
    }

    private void beginApp()
    {
        botaoPareado = (Button)findViewById(R.id.botaoIniciar);
        listaDev = (ListView)findViewById(R.id.list_view);
        textTop = (TextView) findViewById(R.id.txt1);
        /**
         * If the button botaoPareado is clicked, then the program will check if the Bluetooth is Active and begin
         * the search for new devices.
         */
        botaoPareado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /**
                 * The variable meuBT receive the handle to the Bluetooth adapter of the Android Device.
                 */
                meuBT = BluetoothAdapter.getDefaultAdapter();
                /**
                 * If meuBT is null, then it is avoided to use the Bluetooth Adapter.
                 */
                if(meuBT == null)
                {
                    Toast.makeText(MainActivity.this, "Bluetooth cannot be used by this device! :(", Toast.LENGTH_SHORT).show();
                    finish();
                }
                /**
                 * If there is a Active Bluetooth and it is not enable, the is requested to enable the Bluetooth Adapter.
                 */
                else if(!meuBT.isEnabled())
                {
                    /**
                     * An Intent is called to resquest to the user permission to Activate the Bluetooth Device. This is done to
                     * the user can Activate the Bluetooth without exiting the application.
                     */
                    //Intent LigaBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    //startActivityForResult(LigaBT,1);

                    String buttonChange = BluetoothAdapter.ACTION_STATE_CHANGED;
                    String actionReq = BluetoothAdapter.ACTION_REQUEST_ENABLE;
                    IntentFilter filter = new IntentFilter(buttonChange);
                    registerReceiver(btState,filter);
                    startActivityForResult(new Intent(actionReq),0);
                    //devicesPaired();
                }else
                {
                    String address = meuBT.getAddress();
                    String name = meuBT.getName();
                    String statusText = address + ':' + name + '\n';
                    textTop.setText(statusText);
                }


            }
        });
    }

    private void devicesPaired()
    {
        Pareados = meuBT.getBondedDevices();
        ArrayList lista = new ArrayList();

        Toast.makeText(MainActivity.this, "HAHAHA -> Chegou aqui pourra\n", Toast.LENGTH_SHORT).show();

        //meuBT.startDiscovery();

//        if(Pareados.size() > 0)
//        {
//
//            //for(BluetoothDevice i : Pareados)
//            for(BluetoothDevice i : filha da puta Pareados)
//            {
//                lista.add(i.getName() + "\n" + i.getAddress());
//                // Nome do disopsitivo e endereco dele
//            }
//
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "Nenhum modulo bluetooth foi encontrado!",Toast.LENGTH_LONG).show();
//        }



    }

//    private void devicesPaired()
//    {
//        Pareados = meuBT.getBondedDevices();
//        ArrayList lista = new ArrayList();
//
//        if(Pareados.size() > 0)
//        {
//
//            for(BluetoothDevice i : Pareados)
//            {
//                lista.add(i.getName() + "\n" + i.getAddress());
//                // Nome do disopsitivo e endereco dele
//            }
//
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "Nenhum modulo bluetooth foi encontrado!",Toast.LENGTH_LONG).show();
//        }
//
//
//
//    }




//        private void dispositivosPareados()
//        {
//            Pareados = meuBT.getBondedDevices();
//            ArrayList lista = new ArrayList();
//
//            if(Pareados.size() > 0)
//            {
//                BluetoothDevice i;
//                for(i : Pareados)
//                {
//                    lista.add(i.getName() + "\n" + i.getAddress());
//                    // Nome do disopsitivo e endereco dele
//                }
//
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(), "Nenhum modulo bluetooth foi encontrado!",Toast.LENGTH_LONG).show();
//            }
//            final ArrayAdapter ap = new ArrayAdapter(this,android.R.layout.simple_list_item_1,lista);
//
//            listaDev.setAdapter(ap);
//
//            listaDev.setAdapter(ap);
//            listaDev.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked
//
//        }



//
//        // Verificar melhor essa parte
//        private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
//        {
//            public void onItemClick (AdapterView av, View v, int arg2, long arg3)
//            {
//                // Get the device MAC address, the last 17 chars in the View
//                String info = ((TextView) v).getText().toString();
//                String address = info.substring(info.length() - 17);
//                // Make an intent to start next activity.
//                Intent i = new Intent(listaDev.this, ledControl.class);
//                //Change the activity.
//                i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
//                startActivity(i);
//            }
//        };



}
