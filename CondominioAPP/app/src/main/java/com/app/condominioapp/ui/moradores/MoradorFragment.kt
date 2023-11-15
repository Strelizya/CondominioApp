package com.app.condominioapp.ui.moradores

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.condominioapp.R
import com.app.condominioapp.data.Morador
import com.app.condominioapp.databinding.FragmentMoradorBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoradorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel : MoradorViewModel by activityViewModels()
        val binding = FragmentMoradorBinding.inflate((layoutInflater))

        val morador = viewModel.morador
        binding.inputNome.setText(morador.nome)
        binding.inputBloco.setText(morador.Bloco)
        binding.inputApartamento.setText(morador.Apartamento)
        binding.inputGaragem.setText(morador.Garagem)
        binding.inputContato.setText(morador.Contato)

        val checkBoxPet = binding.root.findViewById<CheckBox>(R.id.checkPet)

        if (morador.Pet) {
            checkBoxPet.isChecked = true
        }

        binding.Salvar.setOnClickListener{
            val moradorSalvar = Morador(
                morador.id,
                morador.docId,
                binding.inputNome.text.toString(),
                binding.inputBloco.text.toString(),
                binding.inputApartamento.text.toString(),
                binding.inputGaragem.text.toString(),
                binding.inputContato.text.toString(),
                Pet = checkBoxPet.isChecked
            )
            viewModel.morador = moradorSalvar
            viewModel.salvar()
            findNavController().popBackStack()
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