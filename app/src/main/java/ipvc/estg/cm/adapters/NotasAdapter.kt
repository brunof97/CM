package ipvc.estg.cm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.cm.Notas.Notas
import ipvc.estg.cm.R

class NotasAdapter internal constructor(
    context:Context,
        private val cellClickListener: CellClickListener

):RecyclerView.Adapter<NotasAdapter.NotasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var nota = emptyList<Notas>()



    class NotasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val NotasItemViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val NotasItemViewBody: TextView = itemView.findViewById(R.id.textViewBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemVew = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotasViewHolder(itemVew)
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val current = nota[position]
        holder.NotasItemViewTitle.text=current.title
        holder.NotasItemViewBody.text=current.body
        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(position)
        }
    }

    internal fun setNotas(nota:List<Notas>){
        this.nota=nota
        notifyDataSetChanged()
    }

    override fun getItemCount():Int{
        return nota.size
    }

    fun getNotaPosition(position: Int):Notas{
        return nota[position];
    }

    interface CellClickListener {
        fun onCellClickListener(position: Int)
    }

   

}


