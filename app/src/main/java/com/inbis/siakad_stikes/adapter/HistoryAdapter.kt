package com.inbis.siakad_stikes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inbis.siakad_stikes.model.HistoryItem
import com.inbis.siakad_stikes.R

class HistoryAdapter(
    private val historyList: List<HistoryItem>,
    private val onReportClickListener: (HistoryItem) -> Unit // Listener untuk tombol report
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPertemuan: TextView = view.findViewById(R.id.history_pertemuan_ke)
        val txtTanggal: TextView = view.findViewById(R.id.history_tanggal_ke)
        val txtStatus: TextView = view.findViewById(R.id.history_status_ke)
        val btnReport: ImageButton = view.findViewById(R.id.history_report_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_history_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.txtPertemuan.text = item.pertemuan
        holder.txtTanggal.text = item.tanggal
        holder.txtStatus.text = item.status

        // Tambahkan event klik ke tombol report
        holder.btnReport.setOnClickListener {
            onReportClickListener(item) // Memanggil lambda function saat tombol diklik
        }
    }

    override fun getItemCount(): Int = historyList.size
}
