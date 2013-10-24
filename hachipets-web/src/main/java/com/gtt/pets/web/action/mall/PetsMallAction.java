package com.gtt.pets.web.action.mall;

import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.web.action.BaseAction;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-25
 * Time: 上午12:12
 * To change this template use File | Settings | File Templates.
 */
public class PetsMallAction extends BaseAction{
    private static final long serialVersionUID = 1L;

    @Override
    public String doExecute() throws Exception {
        setChannel(ChannelType.CHANNEL_MALL);
        return SUCCESS;
    }
}
