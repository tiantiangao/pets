package com.gtt.pets.dao.account;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.account.AccountEntity;

/**
 * 账号
 *
 * @author tiantiangao
 */
public interface AccountDao extends GenericDao {

	@DaoAction(action = DaoActionType.INSERT)
	int addAccount(@DaoParam("nickname") String nickname, @DaoParam("email") String email,
				   @DaoParam("userIP") String userIP);

	@DaoAction(action = DaoActionType.LOAD)
	AccountEntity loadById(@DaoParam("accountId") int accountId);

	@DaoAction(action = DaoActionType.LOAD)
	AccountEntity loadByNickname(@DaoParam("nickname") String nickname);

}
