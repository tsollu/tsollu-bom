package com.kaddo.components.jpa.service.filter;

/**
 * Class for filtering attributes with {@link Boolean} type. It can be added to a criteria
 * class as a member, to support the following query parameters: <pre>
 *      fieldName.equals=true
 *      fieldName.specified=true
 *      fieldName.specified=false
 *      fieldName.in=true,false
 * </pre>
 */
public class BooleanFilter extends Filter<Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * Constructor for BooleanFilter.
     * </p>
     */
    public BooleanFilter() {
    }

    /**
     * <p>
     * Constructor for BooleanFilter.
     * </p>
     *
     * @param filter a {@link org.muyie.framework.data.jpa.service.filter.BooleanFilter} object.
     */
    public BooleanFilter(final BooleanFilter filter) {
        super(filter);
    }

    /**
     * <p>
     * copy.
     * </p>
     *
     * @return a {@link org.muyie.framework.data.jpa.service.filter.BooleanFilter} object.
     */
    public BooleanFilter copy() {
        return new BooleanFilter(this);
    }

}
