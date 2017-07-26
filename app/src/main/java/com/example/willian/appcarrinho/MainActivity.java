package com.example.willian.appcarrinho;

/**
 * This code will be commented both in English and Portuguese, due to be a graduation program.
 * _____
 * Este código será comentado tanto em Português quanto Inglês, devido a ser um trabalho de graduação.
 */

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

public class MainActivity extends AppCompatActivity {

    /**
     * The variable EnglishLang is responsible to set the Languages of the system. If this variable is true, all messages and
     * texts are in English; otherwise, all of the are in Portuguese.
     * _____
     * A variavel EnglishLang é responsável pela linguagem do sistema. Se esta for verdadeira, então todas as mensagens e
     * textos estarão em Inglês; caso contrário, estarão em Português.
     */
    private final boolean EnglishLang = true;

    public Button botaoPareado, botaoDev;
    public ListView listaDev;
    public TextView textTop;

    private BluetoothAdapter meuBT = null;
    public Set<BluetoothDevice> Pareados = null;




    // Create a BroadcastReceiver for ACTION_FOUND
    public final BroadcastReceiver btState = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                // Get the BluetoothDevice object from the Intent
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                // Add the name and address to an array adapter to show in a ListView
//                Pareados.add(device.getName() + "\n" + device.getAddress());
//            }
            if (action.equals(meuBT.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                String newToast;
                switch (state) {
                    /**
                     * The Bluetooth is begin turnig on. A message will send by the Toast.
                     */
                    case (BluetoothAdapter.STATE_TURNING_ON): {
                        if(EnglishLang) newToast = "Bluetooth is turning on!)";
                        else newToast = "Bluetooth está sendo ligado!";
                        Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    /**
                     * The Bluetooth is begin turnig off. A message will send by the Toast.
                     */
                    case (BluetoothAdapter.STATE_TURNING_OFF): {
                        if(EnglishLang) newToast = "Bluetooth is turning off!";
                        else newToast = "Bluetooth está sendo desligado!";
                        Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    /**
                     * The Bluetooth is activated. The program will begin.
                     */
                    case (BluetoothAdapter.STATE_ON): {
                        // TESTE
//                        String address = meuBT.getAddress();
//                        String name = meuBT.getName();
//                        String statusText = address + ':' + name + '\n';
//                        textTop.setText(statusText);
                        // TESTE
                        if(EnglishLang) textTop.setText("Bluetooth Adapter is ON! Press the button to turn it off.");
                        else textTop.setText("Adaptador Bluetoot está LIGADO! Pressione o botão para desligar.");
                        /**
                         * Next, call the function to Pair the devices.
                         */
                        //devicesPaired();
                        break;
                    }
                    /**
                     * The Bluetooth is off.
                     */
                    case (BluetoothAdapter.STATE_OFF): {
                        if(EnglishLang) textTop.setText("Bluetooth is OFF! Press the button to turn it on.");
                        else textTop.setText("Adaptador Bluetooth está DESLIGADO! Pressionne o botão para ligar.");
                        break;
                    }

                }
            }
        }
    };


    // STAND BY
//    BroadcastReceiver btState = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String previousState = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
//            String nowState = BluetoothAdapter.EXTRA_STATE;
//            int state = intent.getIntExtra(nowState, -1);
//            String newToast = "";
//
//            switch (state) {
//                /**
//                 * The Bluetooth is begin turnig on. A message will send by the Toast.
//                 */
//                case (BluetoothAdapter.STATE_TURNING_ON): {
//                    newToast = "Bluetooth is turning on! :)";
//                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                /**
//                 * The Bluetooth is begin turnig off. A message will send by the Toast.
//                 */
//                case (BluetoothAdapter.STATE_TURNING_OFF): {
//                    newToast = "Bluetooth is turning off! :(";
//                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
//                    break;
//                }
//                /**
//                 * The Bluetooth is activated. The program will begin.
//                 */
//                case (BluetoothAdapter.STATE_ON): {
//                    // TESTE
//                    String address = meuBT.getAddress();
//                    String name = meuBT.getName();
//                    String statusText = address + ':' + name + '\n';
//                    textTop.setText(statusText);
//                    // TESTE
//                    newToast = "Bluetooth is ON! :)";
//                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
//                    /**
//                     * Next, call the function to Pair the devices.
//                     */
//                    devicesPaired();
//                    break;
//                }
//                /**
//                 * The Bluetooth is off. The application will be closed.
//                 */
//                case (BluetoothAdapter.STATE_OFF): {
//                    newToast = "Bluetooth is OFF! Program will shut down in a few...";
//                    Toast.makeText(MainActivity.this, newToast, Toast.LENGTH_SHORT).show();
//                    finish();
//                    break;
//                }
//            }
//        }
//    };


        // VER MELHOR ESSA PARADA DE oNDESTROY
        //@Override
    protected void onDestroy(Bundle savedInstanceState) {
        super.onDestroy();
        unregisterReceiver(btState);
    }



        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginApp();
    }



    private void beginApp()
    {
        botaoPareado = (Button)findViewById(R.id.botaoIniciar);
        botaoDev = (Button)findViewById(R.id.botaoConectar);
        listaDev = (ListView)findViewById(R.id.list_view);
        textTop = (TextView) findViewById(R.id.txt1);

        if(EnglishLang == true) botaoPareado.setText("Turn On/ Off");
        else botaoPareado.setText("Ligar/ Desligar");

        //// REVER ESSE CASO
        /**
         * If the button botaoPareado is clicked, then the program will check if the Bluetooth is Active and begin
         * the search for new devices; or it will disable the Bluetooth, if it was turned on.
         * _____
         * Se o botão botaoPareado é clicado, então o programa verificará se o Bluetooth está Ativo e começará a procura por
         */
        botaoPareado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /**
                 * The variable meuBT receive the handle to the Bluetooth adapter of the Android Device.
                 * _____
                 * A variável meuBT recebe o handle do Adaptador de Bluetooth do dispositivo Android.
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
                 * _____
                 * Se há um dispositivo Bluetooth ativa mas não habilitado, então é requisitado a habilitação deste adaptador.
                 */
                if(!meuBT.isEnabled())
                {
                    /**
                     * An Intent is called to resquest to the user permission to Activate the Bluetooth Device. This is done to
                     * the user can Activate the Bluetooth without exiting the application.
                     */

                    Intent LigaBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    //startActivityForResult(LigaBT,1);
                    startActivity(LigaBT);

                    /**
                     * The IntentFilter is used to checking changes on the Bluetooth state.
                     * _____
                     * O IntentFilter é utilizado para monitorar mudanças no estado do Bluetooth.
                     */
                    IntentFilter IntF = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                    registerReceiver(btState,IntF);
//                    String nowState = BluetoothAdapter.EXTRA_STATE;
//                    int state = LigaBT.getIntExtra(nowState, -1);
//
//                    /**
//                     * If the Device was activated correctly, then another function will be called.
//                     */
//                    if(state == BluetoothAdapter.STATE_ON) {
//                        Toast.makeText(MainActivity.this, "Bluetooth is ON! :)", Toast.LENGTH_SHORT).show();
//                        /**
//                         * Next, call the function to Pair the devices.
//                         */
//                        //devicesPaired();
//                    }

//                    String buttonChange = BluetoothAdapter.ACTION_STATE_CHANGED;
//                    String actionReq = BluetoothAdapter.ACTION_REQUEST_ENABLE;
//                    IntentFilter filter = new IntentFilter(buttonChange);
//                    registerReceiver(btState,filter);
//                    startActivityForResult(new Intent(actionReq),0);


                    //devicesPaired();
                }
                /**
                 * If the Bluetooth is enabled, then it will turn it off.
                 * _____
                 * Se o Bluetooth está habilitado, então isso irá desativá-lo.
                 */
                if(meuBT.isEnabled())
                {
                    meuBT.disable();

                    IntentFilter IntF = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                    registerReceiver(btState,IntF);
                }



                /**
                 * The Bluetooth device is already Active.
                 */
//                if(meuBT.isEnabled())
//                {
//                    String address = meuBT.getAddress();
//                    String name = meuBT.getName();
//                    String statusText = address + ':' + name + '\n';
//                    textTop.setText("Connected!");
//
//                    devicesPaired();
//                }


                //Toast.makeText(MainActivity.this, "Fim\n", Toast.LENGTH_SHORT).show();
                //if(meuBT.isEnabled()) devicesPaired();

            }
        });
    }

//    private void devicesPaired()
//    {
//        Pareados = meuBT.getBondedDevices();
//        ArrayList lista = new ArrayList();
//
//        Toast.makeText(MainActivity.this, "HAHAHA -> Chegou aqui\n", Toast.LENGTH_SHORT).show();
//
//        //meuBT.startDiscovery();
//
////        if(Pareados.size() > 0)
////        {
////
////            //for(BluetoothDevice i : Pareados)
////            for(BluetoothDevice i : filha da puta Pareados)
////            {
////                lista.add(i.getName() + "\n" + i.getAddress());
////                // Nome do disopsitivo e endereco dele
////            }
////
////        }
////        else
////        {
////            Toast.makeText(getApplicationContext(), "Nenhum modulo bluetooth foi encontrado!",Toast.LENGTH_LONG).show();
////        }
//
//
//
//    }

    private void devicesPaired()
    {

        textTop.setText("AEEEHOOOOOOOOOOOO");

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
//
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(), "Nenhum modulo bluetooth foi encontrado!",Toast.LENGTH_LONG).show();
//        }



    }




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
