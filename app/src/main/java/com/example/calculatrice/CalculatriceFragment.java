package com.example.calculatrice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.calculatrice.ViewModele.ViewModele;
import com.example.calculatrice.databinding.ActivityMainBinding;
import com.example.calculatrice.databinding.FragmentCalculatriceBinding;



public class CalculatriceFragment extends Fragment {
    private FragmentCalculatriceBinding binding;
    private ViewModele viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCalculatriceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView textView = binding.res;

        binding.buttonBack.setOnClickListener(v -> NavHostFragment.findNavController(CalculatriceFragment.this)
                .navigate(R.id.action_CalculatriceFragment_to_FirstFragment));

        ViewModele viewModel = new ViewModelProvider(this).get(ViewModele.class);
        viewModel.getDisplayValue().observe(getViewLifecycleOwner(), textView::setText);

        // pour le binding
        binding.button0.setOnClickListener(v->viewModel.onNumberClick("0"));
        binding.button1.setOnClickListener(v->viewModel.onNumberClick("1"));
        binding.button2.setOnClickListener(v->viewModel.onNumberClick("2"));
        binding.button3.setOnClickListener(v->viewModel.onNumberClick("3"));
        binding.button4.setOnClickListener(v->viewModel.onNumberClick("4"));
        binding.button5.setOnClickListener(v->viewModel.onNumberClick("5"));
        binding.button6.setOnClickListener(v->viewModel.onNumberClick("6"));
        binding.button7.setOnClickListener(v->viewModel.onNumberClick("7"));
        binding.button8.setOnClickListener(v->viewModel.onNumberClick("8"));
        binding.button9.setOnClickListener(v->viewModel.onNumberClick("9"));
        //operation
        binding.buttonAdd.setOnClickListener(v->viewModel.onOperatorClick("+"));
        binding.buttonSous.setOnClickListener(v->viewModel.onOperatorClick("-"));
        binding.buttonMult.setOnClickListener(v->viewModel.onOperatorClick("*"));
        binding.buttonDiv.setOnClickListener(v->viewModel.onOperatorClick("/"));
        binding.buttonDot.setOnClickListener(v->viewModel.onDotClick());
        //equal effacer et nettoyer champ
        binding.buttonEq.setOnClickListener(v->viewModel.onEqualClick());
        binding.buttonDelete.setOnClickListener(v->viewModel.onDeleteClick());
        binding.buttonClear.setOnClickListener(v->viewModel.onClearClick());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
