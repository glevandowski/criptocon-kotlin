package levandowski.gustavo.criptocon.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_about.view.*
import levandowski.gustavo.criptocon.R

class AboutFragment : Fragment() {
     lateinit var buttonFacebook:Button
     lateinit var buttonGitHubOne:Button
     lateinit var buttonGitHubTwo:Button
     lateinit var buttonGitHubThree:Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view:View = inflater.inflate(R.layout.fragment_about, container, false)
       this.findViews(view)
       this.actionEvents()
       return view
    }

    fun findViews(view:View){
        buttonFacebook = view.findViewById(R.id.about_btn_tree)
        buttonGitHubOne = view.findViewById(R.id.button_git_one)
        buttonGitHubTwo = view.findViewById(R.id.button_git_two)
        buttonGitHubThree = view.findViewById(R.id.button_git_three)

        view.card_one_about_fragment.setupAnimations()
        view.card_two_about_fragment.setupAnimations()
    }

    fun actionEvents(){
        this.buttonFacebook.onClickExternalUrl("https://www.facebook.com/CriptoCon39/")

        this.buttonGitHubOne.onClickExternalUrl("https://github.com/thiagocury")

        this.buttonGitHubTwo.onClickExternalUrl("https://github.com/ViniPrimao")

        this.buttonGitHubThree.onClickExternalUrl("https://github.com/glevandowski")
    }


    fun CardView.setupAnimations() = this.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.fadein))


    fun Button.onClickExternalUrl(string:String) = this.setOnClickListener{ string.openExternalUrl()}

    fun String.openExternalUrl() = startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(this)))
}