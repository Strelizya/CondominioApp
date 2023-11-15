package com.app.condominioapp.ui.moradores

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador
import com.app.condominioapp.data.MoradorRepository

import com.app.condominioapp.ui.moradores.placeholder.PlaceholderContent.PlaceholderItem
import com.app.condominioapp.databinding.FragmentItemMoradoresBinding

class MoradoresAdapter(private val moradores: List<Morador>, val viewModel : MoradorViewModel):
    RecyclerView.Adapter<MoradoresAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemMoradoresBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemMorador = moradores[position]

        holder.txtNome.text = itemMorador.nome
        holder.txtBloco.text = itemMorador.Bloco
        holder.txtApartamento.text = itemMorador.Apartamento
        holder.txtGaragem.text = itemMorador.Garagem

        holder.itemView.setOnClickListener{view ->
            viewModel.editar(itemMorador)
            val action = MoradoresFragmentDirections.actionNavHomeToMoradorDetailsFragment()
            view.findNavController().navigate(action)

        }

        //Adicionar opção de cadastra visita
        holder.itemView.setOnLongClickListener {view ->
            AlertDialog.Builder(view.context)
                .setMessage("Deseja apagar?")  //MUDAR
                .setPositiveButton("Confirmar"){ dialog, id -> //MUDAR
                    viewModel.excluir(itemMorador)
                }
                .setNegativeButton("Cancelar") { dialog, id -> //MUDAR
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = moradores.size

    inner class ViewHolder(binding: FragmentItemMoradoresBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val txtNome: TextView = binding.txtNome
        val txtBloco: TextView = binding.txtBloco
        val txtApartamento: TextView = binding.txtApartamento
        val txtGaragem: TextView = binding.txtGaragem


    }

}