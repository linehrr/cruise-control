/*
 * Copyright 2018 LinkedIn Corp. Licensed under the BSD 2-Clause License (the "License"). See License in the project root for license information.
 */

package com.linkedin.kafka.cruisecontrol.servlet.parameters;

import com.linkedin.kafka.cruisecontrol.config.KafkaCruiseControlConfig;
import com.linkedin.kafka.cruisecontrol.servlet.EndPoint;
import com.linkedin.kafka.cruisecontrol.servlet.UserTaskManager;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;


/**
 * Parameters for {@link com.linkedin.kafka.cruisecontrol.servlet.EndPoint#USER_TASKS}
 *
 * <pre>
 * Retrieve the recent user tasks.
 *    GET /kafkacruisecontrol/user_tasks?json=[true/false]&amp;user_task_ids=[Set-of-USER-TASK-IDS]&amp;client_ids=[Set-of-ClientIdentity]&amp;
 *    endpoints=[Set-of-{@link EndPoint}]&amp;types=[Set-of-{@link UserTaskManager.TaskState}]&amp;entries=[POSITIVE-INTEGER]
 *    &amp;fetch_completed_task=[true/false]
 * </pre>
 */
public class UserTasksParameters extends AbstractParameters {
  private Set<UUID> _userTaskIds;
  private Set<String> _clientIds;
  private Set<EndPoint> _endPoints;
  private Set<UserTaskManager.TaskState> _types;
  private int _entries;
  private boolean _fetchCompletedTask;

  public UserTasksParameters(HttpServletRequest request, KafkaCruiseControlConfig config) {
    super(request, config);
  }

  @Override
  protected void initParameters() throws UnsupportedEncodingException {
    super.initParameters();
    _userTaskIds = ParameterUtils.userTaskIds(_request);
    _clientIds = ParameterUtils.clientIds(_request);
    _endPoints = ParameterUtils.endPoints(_request);
    _types = ParameterUtils.types(_request);
    _entries = ParameterUtils.entries(_request);
    _fetchCompletedTask = ParameterUtils.fetchCompletedTask(_request);
  }

  public Set<UUID> userTaskIds() {
    return _userTaskIds;
  }

  public Set<String> clientIds() {
    return _clientIds;
  }

  public Set<EndPoint> endPoints() {
    return _endPoints;
  }

  public Set<UserTaskManager.TaskState> types() {
    return _types;
  }

  public int entries() {
    return _entries;
  }

  public boolean fetchCompletedTask() {
    return _fetchCompletedTask;
  }
}
