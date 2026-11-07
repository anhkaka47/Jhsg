package vn.demo.aioverlay

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

class FloatingMenuService : Service() {
    private var windowManager: WindowManager? = null
    private var floatingView: LinearLayout? = null

    override fun onCreate() {
        super.onCreate()
        if (!android.provider.Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }

        windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val panel = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(10, 10, 10, 10)
        }
        val openButton = Button(this).apply {
            text = "AI"
            setOnClickListener {
                Toast.makeText(this@FloatingMenuService, "Menu nổi đang hoạt động", Toast.LENGTH_SHORT).show()
            }
        }
        val closeButton = Button(this).apply {
            text = "Đóng"
            setOnClickListener { stopSelf() }
        }
        panel.addView(openButton)
        panel.addView(closeButton)

        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        else @Suppress("DEPRECATION") WindowManager.LayoutParams.TYPE_PHONE

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            x = 20
            y = 120
        }
        floatingView = panel
        windowManager?.addView(panel, params)
    }

    override fun onDestroy() {
        floatingView?.let { view ->
            try { windowManager?.removeView(view) } catch (_: Exception) { }
        }
        floatingView = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
