package productions.pudl.siege.Fragment.Operators;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import productions.pudl.siege.R;

public class Operators extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.operators_fragment, container, false);
        loadOperatorImages(view);
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
                Log.v("OperatorClick", "SAS");
                OperatorTabbedDialog tabbedDialog = new OperatorTabbedDialog();
            }
        });
        ImageView fbiswatImage = (ImageView) view.findViewById(R.id.fbiswat);
        Picasso.get().load(R.drawable.fbiswat).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(fbiswatImage);
        fbiswatImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "FBI SWAT");
            }
        });
        ImageView gignImage = (ImageView) view.findViewById(R.id.gign);
        Picasso.get().load(R.drawable.gign).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gignImage);
        gignImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "GIGN");
            }
        });
        ImageView spetsnazImage = (ImageView) view.findViewById(R.id.spetsnaz);
        Picasso.get().load(R.drawable.spetsnaz).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(spetsnazImage);
        spetsnazImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "Spetsnaz");
            }
        });
        ImageView gsg9Image = (ImageView) view.findViewById(R.id.gsg9);
        Picasso.get().load(R.drawable.gsg9).fit().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gsg9Image);
        gsg9Image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "GSG9");
            }
        });
        ImageView jtf2Image = (ImageView) view.findViewById(R.id.jtf2);
        Picasso.get().load(R.drawable.jtf2).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(jtf2Image);
        jtf2Image.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "JTF2");
            }
        });
        ImageView navysealImage = (ImageView) view.findViewById(R.id.navyseal);
        Picasso.get().load(R.drawable.navyseals).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(navysealImage);
        navysealImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "Navy Seal");
            }
        });
        ImageView bopeImage = (ImageView) view.findViewById(R.id.bope);
        Picasso.get().load(R.drawable.bope).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(bopeImage);
        bopeImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "BOPE");
            }
        });
        ImageView satImage = (ImageView) view.findViewById(R.id.sat);
        Picasso.get().load(R.drawable.sat).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(satImage);
        satImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "SAT");
            }
        });
        ImageView geoImage = (ImageView) view.findViewById(R.id.geo);
        Picasso.get().load(R.drawable.geo).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(geoImage);
        geoImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "GEO");
            }
        });
        ImageView sduImage = (ImageView) view.findViewById(R.id.sdu);
        Picasso.get().load(R.drawable.sdu).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(sduImage);
        sduImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "SDU");
            }
        });
        ImageView gromImage = (ImageView) view.findViewById(R.id.grom);
        Picasso.get().load(R.drawable.grom).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gromImage);
        gromImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "GROM");
            }
        });
        ImageView smbImage = (ImageView) view.findViewById(R.id.smb);
        Picasso.get().load(R.drawable.smb).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(smbImage);
        smbImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "SMB");
            }
        });
        ImageView cbrnImage = (ImageView) view.findViewById(R.id.cbrn);
        Picasso.get().load(R.drawable.cbrn).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(cbrnImage);
        cbrnImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "CBRN");
            }
        });
        ImageView gisImage = (ImageView) view.findViewById(R.id.gis);
        Picasso.get().load(R.drawable.gis).fit().centerInside().placeholder(R.drawable.ic_operators).error(R.drawable.ic_profile).into(gisImage);
        gisImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.v("OperatorClick", "GIS");
            }
        });
    }
}