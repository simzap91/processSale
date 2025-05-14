package se.gows.processsale.view;

import se.gows.processsale.utils.RevenueObserver;

public class RevenueView implements RevenueObserver {
    @Override
    public void update(double totalRevenue) {
        System.out.printf(">>> Total Revenue : ", totalRevenue);
    }
}