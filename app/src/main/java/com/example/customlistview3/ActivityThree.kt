package com.example.customlistview3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityThree : AppCompatActivity() {

    private lateinit var toolbarThree: Toolbar
    private lateinit var productPriceETThree: TextView
    private lateinit var productNameETThree: TextView
    private lateinit var editImageIVThree: ImageView
    private lateinit var productDescriptionETThree: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        productDescriptionETThree = findViewById(R.id.productDescriptionETThree)
        toolbarThree = findViewById(R.id.toolbarThree)
        productPriceETThree = findViewById(R.id.productPriceETThree)
        productNameETThree = findViewById(R.id.productNameETThree)
        editImageIVThree = findViewById(R.id.editImageIVThree)
        title = "Описание продукта"
        setSupportActionBar(toolbarThree)

        val product: Product =intent.extras?.getSerializable("product") as Product
        val products = intent.extras?.getSerializable("products")
        val item = intent.extras?.getInt("position")
        var check = intent.extras?.getBoolean("check")

        val name = product.name
        val price = product.price
        val description = product.description
        val image: Uri? = Uri.parse(product.image)

        productNameETThree.setText(name)
        productPriceETThree.setText(price)
        productDescriptionETThree.setText(description)
        editImageIVThree.setImageURI(image)





    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_three, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_exit -> {finishAffinity()
            Toast.makeText(applicationContext, "Работа завершена", Toast.LENGTH_LONG).show()}

                    R.id.menu_back -> {
val intent = Intent(this, ActivityTwo::class.java)
                startActivity(intent)
                        Toast.makeText(applicationContext, "Возврат на главный экран", Toast.LENGTH_LONG).show()


        }
                    }
        return super.onOptionsItemSelected(item)
    }
}
