package com.gtt.pets.service.account;

import com.gtt.pets.bean.account.AccountDTO;
import com.gtt.pets.bean.account.ThirdUserDTO;

/**
 * 账号服务
 *
 * @author tiantiangao
 */
public interface AccountService {

	/**
	 * 创建用户
	 *
	 * @param nickname
	 * @param thirdType
	 * @param thirdId
	 * @param token
	 * @param userIP
	 * @return
	 */
	int addAccount(String nickname, String email, int thirdType, String thirdId, String token, String userIP);

	/**
	 * 根据账号ID获取
	 *
	 * @param accountId
	 * @return
	 */
	AccountDTO loadByAccountID(int accountId);

	/**
	 * 根据第三方ID获取
	 *
	 * @param thirdType
	 * @param thirdId
	 * @return
	 */
	AccountDTO loadByThirdId(int thirdType, String thirdId);

	/**
	 * 根据昵称获取
	 *
	 * @param nickname
	 * @return
	 */
	AccountDTO loadByNickname(String nickname);

	/**
	 * 根据账号ID获取第三方信息
	 *
	 * @param accountId
	 * @param thirdType
	 * @return
	 */
	ThirdUserDTO loadByAccountID(int accountId, int thirdType);

	/**
	 * 更新第三方Token
	 *
	 * @param accountId
	 * @param thirdType
	 * @param thirdUserId
	 * @param accessToken
	 */
	void updateToken(int accountId, int thirdType, String thirdUserId, String accessToken);
}
