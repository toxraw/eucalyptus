/*************************************************************************
 * Copyright 2009-2014 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ************************************************************************/
package com.eucalyptus.objectstorage.policy;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONException;

import com.eucalyptus.auth.policy.ern.Ern;
import com.eucalyptus.auth.policy.ern.ServiceErnBuilder;

/**
 *
 */
public class S3ErnBuilder extends ServiceErnBuilder {

  public static final Pattern RESOURCE_PATTERN = Pattern.compile("([^\\s/]+)(?:(/\\S+))?");

  public static final int ARN_PATTERNGROUP_S3_BUCKET = 1;
  public static final int ARN_PATTERNGROUP_S3_OBJECT = 2;

  public S3ErnBuilder() {
    super(Collections.singleton("s3"));
  }

  @Override
  public Ern build(final String ern, final String service, final String region, final String account, final String resource) throws JSONException {
    final Matcher matcher = RESOURCE_PATTERN.matcher(resource);
    if (matcher.matches()) {
      String bucket = matcher.group(ARN_PATTERNGROUP_S3_BUCKET);
      String object = matcher.group(ARN_PATTERNGROUP_S3_OBJECT);
      return new S3ResourceName(account, bucket, object);
    }
    throw new JSONException("'" + ern + "' is not a valid ARN");
  }
}
