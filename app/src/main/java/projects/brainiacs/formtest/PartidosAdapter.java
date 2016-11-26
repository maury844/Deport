package projects.brainiacs.formtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import projects.brainiacs.formtest.Models.Partido;

/**
 * Created by Mauricio on 22/11/2016.
 */

public class PartidosAdapter extends BaseAdapter{

    Context context;
    List<Partido> data;
    private static LayoutInflater inflater = null;

    public PartidosAdapter(Context context, List<Partido> partidos) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = partidos;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.partido, null);

        //Header textViews
        TextView txtHora = (TextView) vi.findViewById(R.id.textViewHeaderHora);
        TextView txtFecha = (TextView) vi.findViewById(R.id.textViewHeaderFecha);

        //Body textViews
        TextView txtEquipo1 = (TextView) vi.findViewById(R.id.textViewEquipo1);
        TextView txtEquipo2 = (TextView) vi.findViewById(R.id.textViewEquipo2);

        txtHora.setText(data.get(position).getHorario().toString());
        txtFecha.setText(data.get(position).getFecha().toString());
        txtEquipo1.setText(data.get(position).getEquipo(0).toString());
        txtEquipo2.setText(data.get(position).getEquipo(1).toString());

        return vi;
    }

}
