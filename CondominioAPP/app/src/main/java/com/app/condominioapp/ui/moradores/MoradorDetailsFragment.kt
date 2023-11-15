package com.app.condominioapp.ui.moradores

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import android.widget.ToggleButton
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador
import com.app.condominioapp.databinding.FragmentMoradorBinding
import com.app.condominioapp.databinding.FragmentMoradorDetailsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MoradorDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewModel : MoradorViewModel by activityViewModels()
        val binding = FragmentMoradorDetailsBinding.inflate((layoutInflater))

        val morador = viewModel.morador

        binding.txtNomeDT.setText(morador.nome)
        binding.txtBlocoDT.setText(morador.Bloco)
        binding.txtApartamentoDT.setText(morador.Apartamento)
        binding.txtGaragemDt.setText(morador.Garagem)
        binding.txtContatoDt.setText(morador.Contato)

        val petStatus = if (morador.Pet) "Sim" else "Não"
        binding.txtPetDt.text = petStatus

        binding.BtnEditar.setOnClickListener {
            viewModel.editar(morador)
            val action = MoradorDetailsFragmentDirections.actionMoradorDetailsFragmentToMoradorFragment()
            view?.findNavController()?.navigate(action)
        }

        hideFab()

        return binding.root
    }

    fun hideFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.visibility = View.INVISIBLE
    }

    fun showFab() {
        val fab = requireActivity().findViewById<FloatingActionButton>(R.id.fab)
        fab.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showFab()
    }

}