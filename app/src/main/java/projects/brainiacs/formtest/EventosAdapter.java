package projects.brainiacs.formtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import projects.brainiacs.formtest.Models.Evento;
import projects.brainiacs.formtest.Models.Partido;

/**
 * Created by Mauricio on 29/10/2016.
 */

public class EventosAdapter extends BaseAdapter{
    Context context;
    List<Evento> data;

    private static LayoutInflater inflater = null;

    public EventosAdapter(Context context, List<Evento> eventos) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = eventos;
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
            vi = inflater.inflate(R.layout.evento, null);

        //Header textViews
        TextView txtNombre = (TextView) vi.findViewById(R.id.textViewHeaderNombre);

        //Body textViews
        TextView txtLugar = (TextView) vi.findViewById(R.id.textViewLugar);
        TextView txtFecha = (TextView) vi.findViewById(R.id.textViewFecha);
        TextView txtDescripcion = (TextView) vi.findViewById(R.id.textViewDescripcion);


        txtNombre.setText(data.get(position).getNombre());
        txtLugar.setText(data.get(position).getLugar());
        txtFecha.setText(data.get(position).getFechaInicio());
        txtDescripcion.setText(data.get(position).getDescripcion());

        return vi;
    }

}
