package io.github.ibuildthecloud.dstack.api.object.postini;

import io.github.ibuildthecloud.dstack.api.auth.Policy;
import io.github.ibuildthecloud.dstack.api.utils.ApiUtils;
import io.github.ibuildthecloud.dstack.core.constants.AccountConstants;
import io.github.ibuildthecloud.dstack.object.postinit.ObjectPostInstantiationHandler;
import io.github.ibuildthecloud.dstack.object.util.ObjectUtils;
import io.github.ibuildthecloud.gdapi.context.ApiContext;

import java.util.Map;

public class AccountFieldPostInitHandler implements ObjectPostInstantiationHandler {

    @Override
    public <T> T postProcess(T obj, Class<T> clz, Map<String, Object> properties) {
        ApiContext apiContext = ApiContext.getContext();
        if ( apiContext == null ) {
            /* Back-end can do whatever it wants */
            return obj;
        }

        Policy policy = ApiUtils.getPolicy();
        boolean overwrite = true;

        if ( policy.isOption(Policy.AUTHORIZED_FOR_ALL_ACCOUNTS) && properties.containsKey(AccountConstants.ACCOUNT_ID) ) {
            overwrite = false;
        }

        if ( overwrite ) {
            properties.put(AccountConstants.ACCOUNT_ID, policy.getAccountId());
        }

        return obj;
    }

    protected void set(Object obj, String property, Object value) {
        ObjectUtils.setPropertyIgnoreErrors(obj, property, value);
    }
}
