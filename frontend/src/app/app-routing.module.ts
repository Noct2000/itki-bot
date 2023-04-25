import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/questions' },
  { path: 'questions', loadChildren: () => import('./pages/questions/questions.module').then(m => m.QuestionsModule) },
  { path: "curators", loadChildren: () => import('./pages/curators/curators.module').then(m => m.CuratorsModule) },
  { path: "telegram-users", loadChildren: () => import('./pages/telegram-users/telegram-users.module').then(m => m.TelegramUsersModule) },
  { path: "send-message", loadChildren: () => import('./pages/send-message/send-message.module').then(m => m.SendMessageModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
