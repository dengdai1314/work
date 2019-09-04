package com.obex.demo.model

/*
 * -----------------------------------------------------------------
 * Copyright (C) 2014-2016, by obex, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: UIBean.java
 * Author: liuhai
 * Version: V100R001C01
 * Create: 2019/7/6 14:54
 * Changes (from 2019/7/6)
 * -----------------------------------------------------------------
 * 2019/7/6 : Create UIBean.java (liuhai);
 * -----------------------------------------------------------------
 * description:
 */
/**
 * wId  调用ShowUi 接口时需要传入显示的哪类UI类型ID
 */
data class UIBean(var wID: Int,var name:String)