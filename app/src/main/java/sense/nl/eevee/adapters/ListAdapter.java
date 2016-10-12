package sense.nl.eevee.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sense.nl.eevee.R;
import sense.nl.eevee.model.User;

/**
 * Created by budi-ahmad-syidiq on 12/10/16.
 * purpose : show data in cardview
 *
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<User> data;
    private Context context;

    public ListAdapter(List<User> d, Context c){
        this.data = d;
        this.context = c;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview,null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

        // binding data
        holder.name.setText(data.get(position).getName().getFirst()+" "+data.get(position).getName().getLast());
        holder.address.setText(data.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        // count the size of data
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView address;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
        }
    }
}
