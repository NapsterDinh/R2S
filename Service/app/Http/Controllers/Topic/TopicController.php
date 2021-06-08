<?php

namespace App\Http\Controllers\Topic;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use App\Services\TopicService;

class TopicController extends Controller
{
    public function __construct(TopicService $topicService) {
        $this->topicService = $topicService;
    }

    public function showTopic(Request $request) {
        return $this->topicService->showTopic();
    }
}