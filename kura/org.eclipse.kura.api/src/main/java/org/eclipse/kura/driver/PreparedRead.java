/**
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Eurotech
 */
package org.eclipse.kura.driver;

import java.util.List;

import org.eclipse.kura.KuraException;
import org.eclipse.kura.driver.Driver.ConnectionException;

/**
 * This interface represents an optimized request that can be performed by a driver.
 * Implementations of this interface are returned by a driver as a result of
 * a call to the {@link Driver#prepareRead(java.util.List)} method.
 * 
 * @see Driver#prepareRead(java.util.List)
 */
public interface PreparedRead extends AutoCloseable {

    /**
     * Performs the optimized read request described by this {@link PreparedRead} instance.
     * In order to improve efficiency this method can return the same {@link DriverRecord} instances that were supplied
     * as arguments to the {@link Driver#prepareRead(List)} call that created this {@link PreparedRead}.
     * The returned records should not be modified while a valid (not closed) {@link PreparedRead} holds a
     * reference to them, otherwise unpredictable behavior can occur.
     * 
     * @return the result of the request as a list of {@link DriverRecord} instances.
     * @throws KuraException
     *             if the provided {@link PreparedRead} is not valid (for example if it has been closed)
     * @throws ConnectionException
     *             if the connection to the field device is interrupted
     */
    public List<DriverRecord> execute() throws ConnectionException, KuraException;

    /**
     * Returns the list of driver records associated with this prepared read.
     * In order to improve efficiency this method can return the same {@link DriverRecord} instances that were supplied
     * as arguments to the {@link Driver#prepareRead(List)} call that created this {@link PreparedRead}.
     * The returned records should not be modified while a valid (not closed) {@link PreparedRead} holds a
     * reference to them, otherwise unpredictable behavior can occur.
     * 
     * @return The list of driver records associated with this prepared read.
     */
    public List<DriverRecord> getDriverRecords();
}
