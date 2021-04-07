package com.chengwf.demo.viewchat.ui.main

import android.view.View
import com.chengwf.demo.R
import com.chengwf.demo.base.BaseFragment

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment(private val layoutId:Int) : BaseFragment() {

    private lateinit var pageViewModel: PageViewModel

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }*/

    override fun viewCreate(view: View) {
        //        val textView: TextView = root.findViewById(R.id.section_label)
//        pageViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })
    }

    override fun getLayoutResId() = layoutId

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
//            return PlaceholderFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_SECTION_NUMBER, sectionNumber)
//                }
//            }
            return PlaceholderFragment(arrayOf(R.layout.fragment_tab_test_1,R.layout.fragment_tab_test_2,R.layout.fragment_test_3)[sectionNumber])
        }
    }
}