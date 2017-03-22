package com.bootcamp.model;

import com.bootcamp.entity.LuckyDrawPeople;
import com.bootcamp.entity.Prize;

import java.util.List;

/**
 * Created by yaobin on 2017/3/16.
 */
public class LuckDrawModel {
    private Prize prize;
    private LuckyDrawPeople luckyDrawPeople;
    private List<LuckyDrawPeople> luckyDrawPeoples;
    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public LuckyDrawPeople getLuckyDrawPeople() {
        return luckyDrawPeople;
    }

    public void setLuckyDrawPeople(LuckyDrawPeople luckyDrawPeople) {
        this.luckyDrawPeople = luckyDrawPeople;
    }

    public List<LuckyDrawPeople> getLuckyDrawPeoples() {
        return luckyDrawPeoples;
    }

    public void setLuckyDrawPeoples(List<LuckyDrawPeople> luckyDrawPeoples) {
        this.luckyDrawPeoples = luckyDrawPeoples;
    }
}
