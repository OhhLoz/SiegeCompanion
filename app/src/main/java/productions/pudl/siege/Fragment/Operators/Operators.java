package productions.pudl.siege.Fragment.Operators;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import productions.pudl.siege.Adapter.MyUbiAPIAdapter;
import productions.pudl.siege.Adapter.VolleyResponseListener;
import productions.pudl.siege.Data.Operator;
import productions.pudl.siege.R;

public class Operators extends Fragment
{
    HashMap<String, Operator> temp = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.operators_fragment, container, false);
        MyUbiAPIAdapter.create(view.getContext(), "raspberrypicreations@gmail.com:1NnpENN6za61", new VolleyResponseListener()
        {
            @Override
            public void onError(String message)
            {

            }

            @Override
            public void onResponse()
            {
                MyUbiAPIAdapter.getOperators("28ca710b-270d-491b-8073-42654f82745d", "PC", getResources().getStringArray(R.array.operators), new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse() {
                        temp = MyUbiAPIAdapter.getOperatorFinalResult();
                        loadOperatorImages(view);
                    }
                });
                Log.v("onResponseCallback", "GetOperators Run");
            }
        });
        return view;
    }

    private void loadOperatorImages(View view)
    {
        ImageView sasImage = (ImageView) view.findViewById(R.id.sas);
        Picasso.get().load(R.drawable.sas).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(sasImage);
        sasImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "SAS");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("sas", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView fbiswatImage = (ImageView) view.findViewById(R.id.fbiswat);
        Picasso.get().load(R.drawable.fbiswat).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(fbiswatImage);
        fbiswatImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "FBI SWAT");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("fbiswat", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView gignImage = (ImageView) view.findViewById(R.id.gign);
        Picasso.get().load(R.drawable.gign).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gignImage);
        gignImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "GIGN");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("gign", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView spetsnazImage = (ImageView) view.findViewById(R.id.spetsnaz);
        Picasso.get().load(R.drawable.spetsnaz).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(spetsnazImage);
        spetsnazImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "Spetsnaz");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("spetsnaz", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView gsg9Image = (ImageView) view.findViewById(R.id.gsg9);
        Picasso.get().load(R.drawable.gsg9).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gsg9Image);
        gsg9Image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gsg9");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("gsg9", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView jtf2Image = (ImageView) view.findViewById(R.id.jtf2);
        Picasso.get().load(R.drawable.jtf2).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(jtf2Image);
        jtf2Image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "JTF2");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("jtf2", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView navysealImage = (ImageView) view.findViewById(R.id.navyseal);
        Picasso.get().load(R.drawable.navyseals).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(navysealImage);
        navysealImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "navyseals");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("navyseals", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView bopeImage = (ImageView) view.findViewById(R.id.bope);
        Picasso.get().load(R.drawable.bope).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(bopeImage);
        bopeImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "bope");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("bope", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView satImage = (ImageView) view.findViewById(R.id.sat);
        Picasso.get().load(R.drawable.sat).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(satImage);
        satImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "sat");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("sat", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView geoImage = (ImageView) view.findViewById(R.id.geo);
        Picasso.get().load(R.drawable.geo).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(geoImage);
        geoImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "geo");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("geo", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView sduImage = (ImageView) view.findViewById(R.id.sdu);
        Picasso.get().load(R.drawable.sdu).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(sduImage);
        sduImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "sdu");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("sdu", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView gromImage = (ImageView) view.findViewById(R.id.grom);
        Picasso.get().load(R.drawable.grom).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gromImage);
        gromImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "grom");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("grom", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView smbImage = (ImageView) view.findViewById(R.id.smb);
        Picasso.get().load(R.drawable.smb).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(smbImage);
        smbImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "smb");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("smb", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView cbrnImage = (ImageView) view.findViewById(R.id.cbrn);
        Picasso.get().load(R.drawable.cbrn).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(cbrnImage);
        cbrnImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "cbrn");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("cbrn", temp);
                tabbedDialog.show(ft, "dialog");
            }
        });


        ImageView gisImage = (ImageView) view.findViewById(R.id.gis);
        Picasso.get().load(R.drawable.gis).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gisImage);
        gisImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gis");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("gis", temp);
                tabbedDialog.show(ft, "gis");
            }
        });

        ImageView gsutrImage = (ImageView) view.findViewById(R.id.gsutr);
        Picasso.get().load(R.drawable.gsutr).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gsutrImage);
        gsutrImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gsutr");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("gsutr", temp);
                tabbedDialog.show(ft, "gsutr");
            }
        });

        ImageView gigrImage = (ImageView) view.findViewById(R.id.gigr);
        Picasso.get().load(R.drawable.gigr).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gigrImage);
        gigrImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gigr");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("gigr", temp);
                tabbedDialog.show(ft, "gigr");
            }
        });

        ImageView sasrImage = (ImageView) view.findViewById(R.id.sasr);
        Picasso.get().load(R.drawable.sasr).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(sasrImage);
        sasrImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gigr");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("sasr", temp);
                tabbedDialog.show(ft, "sasr");
            }
        });

        ImageView usssjaegImage = (ImageView) view.findViewById(R.id.usssjaeg);
        Picasso.get().load(R.drawable.usssjaeg).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(usssjaegImage);
        usssjaegImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                Log.v("OperatorClick", "gigr");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog("usssjaeg", temp);
                tabbedDialog.show(ft, "USSS / Jaegercorps");
            }
        });
    }
}