#!/bin/bash
BRANCH=${1:-`git rev-parse --abbrev-ref HEAD`}
git fetch
git reset --hard origin/$BRANCH
