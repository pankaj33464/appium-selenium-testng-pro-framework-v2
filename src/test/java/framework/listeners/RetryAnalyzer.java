package framework.listeners;

import framework.core.ConfigManager;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private final int maxRetry = ConfigManager.cfg().retryCount();

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            try { Thread.sleep(500L * count); } catch (InterruptedException ignored) {}
            return true;
        }
        return false;
    }
}
