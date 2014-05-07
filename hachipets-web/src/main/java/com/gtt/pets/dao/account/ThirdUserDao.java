package com.gtt.pets.dao.account;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.account.ThirdUserEntity;

/**
 * 第三方用户Dao
 *
 * @author tiantiangao
 */
public interface ThirdUserDao extends GenericDao {

	@DaoAction(action = DaoActionType.INSERT)
	int addThirdUser(@DaoParam("thirdType") int thirdType, @DaoParam("thirdId") String thirdId,
					 @DaoParam("token") String token, @DaoParam("accountId") int accountId);

	@DaoAction(action = DaoActionType.LOAD)
	ThirdUserEntity loadByThirdID(@DaoParam("thirdType") int thirdType, @DaoParam("thirdId") String thirdId);

	@DaoAction(action = DaoActionType.LOAD)
	ThirdUserEntity loadByAccountID(@DaoParam("thirdType") int thirdType, @DaoParam("accountId") int accountId);

	@DaoAction(action = DaoActionType.UPDATE)
	int updateToken(@DaoParam("accountId") int accountId, @DaoParam("thirdType") int thirdType,
			   @DaoParam("thirdId") String thirdUserId, @DaoParam("token") String accessToken);
}
