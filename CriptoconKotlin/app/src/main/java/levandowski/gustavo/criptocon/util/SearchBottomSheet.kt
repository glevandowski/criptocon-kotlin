package levandowski.gustavo.criptocon.util

import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.FrameLayout
import android.widget.SearchView

class SearchBottomSheet{


    fun setBottomSheet(frameLayout: FrameLayout, float: FloatingActionButton, search: SearchView, manageKeyboard: ManageKeyboard) {
        val bottomSheetBehavior = BottomSheetBehavior.from(frameLayout)

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)

        bottomSheetBehavior.setPeekHeight(500)

        bottomSheetBehavior.setHideable(false)

        float.setOnClickListener { bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED }

        search.setOnQueryTextFocusChangeListener{ view, hasFocus -> if(!hasFocus) bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED }

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> search.showSearchView(float, manageKeyboard)

                    BottomSheetBehavior.STATE_COLLAPSED-> float.showFloatButton(search, manageKeyboard)
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun SearchView.showSearchView(float: FloatingActionButton, manageKeyboard: ManageKeyboard){
        float.setVisibility(View.GONE)
        this.setVisibility(View.VISIBLE)
        this.requestFocusFromTouch()
        manageKeyboard.showKeyBoard()
    }

    private fun FloatingActionButton.showFloatButton(search: SearchView, manageKeyboard: ManageKeyboard) {
        this.setVisibility(View.VISIBLE)
        search.setVisibility(View.GONE)
        manageKeyboard.hideKeyBoard()
    }
}