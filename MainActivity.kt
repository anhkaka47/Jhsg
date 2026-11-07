package vn.demo.aioverlay

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.view.ViewGroup

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }
        val title = TextView(this).apply {
            text = "AI Overlay Assistant"
            textSize = 24f
            gravity = Gravity.CENTER
        }
        val info = TextView(this).apply {
            text = "Cấp quyền hiển thị trên ứng dụng khác để bật nút nổi."
            textSize = 16f
            gravity = Gravity.CENTER
            setPadding(0, 20, 0, 24)
        }
        val permissionButton = Button(this).apply {
            text = "Cấp quyền hiển thị"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")))
            }
        }
        val startButton = Button(this).apply {
            text = "Bật menu nổi"
            setOnClickListener {
                if (Settings.canDrawOverlays(this@MainActivity)) {
                    startService(Intent(this@MainActivity, FloatingMenuService::class.java))
                } else {
                    permissionButton.performClick()
                }
            }
        }
        layout.addView(title, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layout.addView(info)
        layout.addView(permissionButton)
        layout.addView(startButton)
        setContentView(layout)
    }
}
