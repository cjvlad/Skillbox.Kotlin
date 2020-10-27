package com.example.a02_toolbar
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
    }
    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun initToolbar() {
        toolbar.setNavigationOnClickListener {
            toast("Navigation clicked")
        }
        toolbar.setNavigationOnClickListener {
            toast("Navigation clicked ")
        }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_menu_1 -> {
                    toast("Заголовок в меню")
                    true
                }

                R.id.action_2 -> {
                    toast("Ничего не закреплено")
                    true
                }
                else -> false
            }
        }

        val searchItem = toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                return true
            }
        })
        (searchItem.actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener { // создали обьект анонимного класса
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val parts = resources.getString(R.string.airbus1).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    parts.filter{ it.contains(newText ?: "",true) }
                        .joinToString(" ")
                        .let {
                            longTextView.text = it
                        }
                    return true
                }
            })
    }
}