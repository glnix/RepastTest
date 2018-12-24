package ru.goryachev.repast.feature.feed.presentation.feed.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.fmt_restoraunt_feed.*
import ru.goryachev.repast.R
import ru.goryachev.repast.di.DI
import ru.goryachev.repast.feature.feed.domain.RestaurantEntity
import ru.goryachev.repast.feature.feed.presentation.adapter.FeedAdapter
import ru.goryachev.repast.feature.feed.presentation.detail.presenter.FeedPresenter
import ru.goryachev.repast.feature.feed.presentation.detail.view.FeedView
import ru.goryachev.repast.feature.global.presentation.ui.MarginItemDecoration
import ru.goryachev.repast.feature.global.presentation.view.BaseFragment
import toothpick.Toothpick

class FeedFragment : BaseFragment(), FeedView {

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override val layoutRes: Int = R.layout.fmt_restoraunt_feed

    @InjectPresenter
    lateinit var presenter: FeedPresenter

    private val feedAdapter: FeedAdapter by lazy { FeedAdapter(emptyList(), this::dispatchCall, this::dispatchTip, this::dispatchStar) }

    @ProvidePresenter
    fun providePresenter(): FeedPresenter {
        return Toothpick.openScopes(DI.SCOPE_FLOW_MAIN, scopeName)
                .getInstance(FeedPresenter::class.java).also {
                    Toothpick.closeScope(scopeName)
                }
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {
        val llmanager = LinearLayoutManager(context)
        val size = resources.getDimension(R.dimen.padding_medium).toInt()
        val decoration = MarginItemDecoration(size)
        with(feedRv) {
            layoutManager = llmanager
            adapter = feedAdapter
            addItemDecoration(decoration)
        }
    }

    override fun showRestaurants(restaurants: List<Any>) {
        feedAdapter.changeDataSet(restaurants)
    }

    private fun dispatchCall(phoneNumber: String) {
        presenter.callTap(phoneNumber)
    }

    private fun dispatchStar(restaurantEntity: RestaurantEntity) {
        presenter.starTap(restaurantEntity)
    }

    private fun dispatchTip(restaurantEntity: RestaurantEntity) {
        presenter.tipTap(restaurantEntity)
    }
}
