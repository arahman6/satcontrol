package edu.miu.cs.cs489.satcontrol.exception;

import java.time.Instant;

public record ApiError(
        String message,
        String path,
        int statusCode,
        Instant timestamp
) {}
