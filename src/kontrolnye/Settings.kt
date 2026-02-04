package kontrolnye

object Settings {
    var language: String = "English"
    var theme: String = "Light"
    var notificationsEnabled: Boolean = true

    fun applySettings() {
        println("Применение настроек: язык - $language, тема - $theme, уведомления - ${if (notificationsEnabled) "включены" else "выключены"}")
    }
}

class UIComponent {
    private val settings = Settings

    fun updateLanguage(newLanguage: String) {
        settings.language = newLanguage
        println("UIComponent: язык изменен на $newLanguage")
        settings.applySettings()
    }

    fun getSettingsLanguage(): String {
        return settings.language
    }

    fun getSettingsNotificationsEnabled(): Boolean {
        return settings.notificationsEnabled
    }
}

class NotificationComponent {
    private val settings = Settings

    fun toggleNotifications(enable: Boolean) {
        settings.notificationsEnabled = enable
        println("NotificationComponent: уведомления ${if (enable) "включены" else "выключены"}")
        settings.applySettings()
    }

    fun getSettingsLanguage(): String {
        return settings.language
    }

    fun getSettingsNotificationsEnabled(): Boolean {
        return settings.notificationsEnabled
    }
}

class Application {
    private val uiComponent = UIComponent()
    private val notificationComponent = NotificationComponent()

    fun run() {
        uiComponent.updateLanguage("Spanish")
        notificationComponent.toggleNotifications(false)
    }
}