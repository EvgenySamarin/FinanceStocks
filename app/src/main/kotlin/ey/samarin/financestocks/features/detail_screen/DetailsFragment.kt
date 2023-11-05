package ey.samarin.financestocks.features.detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ey.samarin.financestocks.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel by viewModels<DetailsViewModel>()


    private var _binding: FragmentDetailsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                with(binding) {
                    if (uiState.errorText.isNotEmpty()) {
                        Toast.makeText(root.context, uiState.errorText, Toast.LENGTH_LONG).show()
                    }

                    title.text = uiState.title
                    sector.text = uiState.sector
                    address.text = uiState.address
                    longBusinessSummary.text = uiState.longBusinessSummary
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setNavigationParams(stockSymbol: String?) {
        viewModel.setArguments(stockSymbol = stockSymbol)
    }
}