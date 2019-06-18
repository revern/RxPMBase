package com.example.revern.rxpmbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.revern.rxpmbase.ui.yes.YesScreen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router = Conductor.attachRouter(this, screen_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(YesScreen()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}

